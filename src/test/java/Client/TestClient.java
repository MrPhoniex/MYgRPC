package Client;

import org.example.Core.Client.RpcClientProxy;
import org.example.IDE.Hello;
import org.example.IDE.HelloRequest;
import org.example.IDE.HelloResponse;

public class TestClient {
    public static void main(String[] args) {
        // 获取RpcService
        RpcClientProxy proxy = new RpcClientProxy();
        Hello hello = proxy.getService(Hello.class);
        // 构造出请求对象HelloRequest
        HelloRequest request = HelloRequest.builder().msg("CXG").build();
        // rpc调用并返回结果对象HelloResponse
        HelloResponse response = hello.hi(request);
        // 从HelloResponse中获取msg
        String msg = response.getMsg();

        System.out.println(msg);
    }
}
