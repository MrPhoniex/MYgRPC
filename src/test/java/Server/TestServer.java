package Server;

import org.example.Core.Server.RpcServer;
import org.example.IDE.Hello;

import java.sql.SQLOutput;

public class TestServer {
    public static void main(String[] args) {
        // 真正的rpc server
        RpcServer server = new RpcServer();
        // 包含需要处理的方法的对象
        HelloImpl hello = new HelloImpl();
        // 向rpc server注册对象里面的所有方法
        server.register(hello);

        server.serve(9000);
    }
}
