package commons.codec;

public interface IBodyCodec {

    //1.obj --> byte[]
    <T> byte[] serialize(T obj) throws Exception;


    //2.byte[] --> obj
    <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception;


}
