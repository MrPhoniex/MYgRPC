package org.example.Core.RpcProtocol;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcRequest implements Serializable {
    //协议头部分
    private String header;

    //协议体部分
    private byte[] body;

}
