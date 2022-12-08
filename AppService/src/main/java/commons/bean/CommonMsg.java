package commons.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Intermediate for regional <-> colocation communication
 */
@Getter
@Setter
@ToString
public class CommonMsg implements Serializable {

    private int bodyLength;

    private byte checksum;

    private short msgSrc;

    private short msgDst;

    private short msgType;

    private byte status;

    private long msgNo;

    @ToString.Exclude
    private byte[] body;


    ////////////////////////////////
    private boolean isLegal;

    private short errCode;

    private long timestamp;





}
