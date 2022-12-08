package commons.codec;

import com.alipay.remoting.serialization.SerializerManager;
import commons.checksum.ByteCheckSum;

public class BodyCodec implements IBodyCodec {
    @Override
    public <T> byte[] serialize(T obj) throws Exception {
        //1. jdk Serialization //2. json //3.（Hessian2 encode）
        return SerializerManager.getSerializer(SerializerManager.Hessian2).serialize(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception {
        return SerializerManager.getSerializer(SerializerManager.Hessian2).deserialize(bytes,clazz.getName());
    }


    public static void main(String[] args) throws Exception {
        String a = "test";
        String b = "test1";
        String c = "test";
        byte ca = new ByteCheckSum().getChecksum(a.getBytes());
        byte cb = new ByteCheckSum().getChecksum(b.getBytes());
        byte cc = new ByteCheckSum().getChecksum(c.getBytes());
        System.out.println(ca);
        System.out.println(cb);
        System.out.println(cc);


    }

}
