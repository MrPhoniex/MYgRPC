package Server;

import org.example.IDE.Hello;
import org.example.IDE.HelloRequest;
import org.example.IDE.HelloResponse;

public class HelloImpl implements Hello {
    @Override
    public HelloResponse hello(HelloRequest helloRequest) {
        return HelloResponse.builder()
                .msg("hello " + helloRequest.getMsg())
                .build();
    }

    @Override
    public HelloResponse hi(HelloRequest helloRequest) {
        return HelloResponse.builder()
                .msg("hi " + helloRequest.getMsg())
                .build();
    }
}
