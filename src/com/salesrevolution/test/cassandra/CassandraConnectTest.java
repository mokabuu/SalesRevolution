package com.salesrevolution.test.cassandra;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ColumnPath;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KeyRange;
import org.apache.cassandra.thrift.KeySlice;
import org.apache.cassandra.thrift.NotFoundException;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.SliceRange;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author hatanaka
 */
public class CassandraConnectTest{
    /** キースペース */
    public static final String KEYSPACE = "TEST";

    /** カラムファミリー */
    public static final String COLUMN_FAMILY = "Users";

    /**
     * @param args
     */
    public static void main(String[] args) {
        TSocket socket = new TSocket("36.55.245.134", 9160);
        TTransport transport = new TFramedTransport(socket);
        TProtocol protocol = new TBinaryProtocol(transport);
        Cassandra.Client client = new Cassandra.Client(protocol);
        try {
            transport.open();
            client.set_keyspace(KEYSPACE);
            final long timestamp = System.currentTimeMillis();
            //特定キーに１件カラムをインサート
            Column value = new Column(strToBB("sample1"));
            value.setValue(strToBB("サンプルの値"));
            value.setTimestamp(timestamp);
            client.insert(strToBB("test"),
                          new ColumnParent(COLUMN_FAMILY),
                          value,
                          ConsistencyLevel.ONE);
            System.out.println("インサート完了.");

            //特定キーの特定カラムの値を取得
            System.out.println();
            System.out.println("特定キーの特定カラムの値を取得");
            ColumnPath path = new ColumnPath(COLUMN_FAMILY);
            path.setColumn(strToBB("sample1"));
            ColumnOrSuperColumn column = client.get(strToBB("test"),
                                                    path,
                                                    ConsistencyLevel.ONE);
            System.out.println(byteToStr(column.getColumn().getName()) + ":"
                    + byteToStr(column.getColumn().getValue()));

            //特定キーの全カラムの値を取得
            System.out.println();
            System.out.println("特定キーの全カラムの値を取得");
            SlicePredicate predicate = new SlicePredicate();
            predicate.setSlice_range(new SliceRange(ByteBuffer.wrap(new byte[0]),
                                                    ByteBuffer.wrap(new byte[0]),
                                                    false,
                                                    10));
            List<ColumnOrSuperColumn> columns = client.get_slice(strToBB("test"),
                                                                 new ColumnParent(COLUMN_FAMILY),
                                                                 predicate,
                                                                 ConsistencyLevel.ONE);
            for (ColumnOrSuperColumn aColumn : columns) {
                System.out.println(byteToStr(aColumn.getColumn().getName())
                        + ":" + byteToStr(aColumn.getColumn().getValue()));
            }

            //複数キーの全カラムの値を取得
            System.out.println();
            System.out.println("複数キーの全カラムの値を取得");
            KeyRange range = new KeyRange();
            range.setStart_key(new byte[0]);
            range.setEnd_key(new byte[0]);
            List<KeySlice> keys = client.get_range_slices(new ColumnParent(COLUMN_FAMILY),
                                                          predicate,
                                                          range,
                                                          ConsistencyLevel.ONE);
            for (KeySlice key : keys) {
                for (ColumnOrSuperColumn aColumn : key.getColumns()) {
                    System.out.println(byteToStr(key.getKey()) + ":"
                            + byteToStr(aColumn.getColumn().getName()) + ":"
                            + byteToStr(aColumn.getColumn().getValue()));
                }
            }
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e);
        } catch (UnavailableException e) {
            throw new RuntimeException(e);
        } catch (TimedOutException e) {
            throw new RuntimeException(e);
        } catch (TException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                transport.flush();
            } catch (TTransportException e) {
                throw new RuntimeException(e);
            } finally {
                transport.close();
            }
        }
    }

    /**
     * String から ByteBuffer へのコンバータ
     * @param msg String
     * @return ByteBuffer
     */
    private static ByteBuffer strToBB(String msg) {
        Charset charset = Charset.forName("UTF-8");
        return ByteBuffer.wrap(msg.getBytes(charset));
    }

    /**
     * byte[] から String へのコンバータ
     * @param buf byte[]
     * @return String
     */
    private static String byteToStr(byte[] buf) {
        Charset charset = Charset.forName("UTF-8");
        return new String(buf, charset);
    }
}
