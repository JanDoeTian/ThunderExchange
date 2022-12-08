package commons.codec;

import io.vertx.core.buffer.Buffer;
import commons.bean.CommonMsg;

public interface IMsgCodec {

    //TCP <--> CommonMsg
    Buffer encodeToBuffer(CommonMsg msg);

    CommonMsg decodeFromBuffer(Buffer buffer);

}
