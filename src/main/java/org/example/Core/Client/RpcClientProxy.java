package org.example.Core.Client;

import org.example.Core.Codec.RpcRequestBody;
import org.example.Core.Codec.RpcResponseBody;
import org.example.Core.RpcProtocol.RpcRequest;
import org.example.Core.RpcProtocol.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClientProxy implements InvocationHandler {
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1、将调用所需信息编码成byte[]，即有了调用编码【codec层】
        RpcRequestBody rpcRequestBody = RpcRequestBody.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(rpcRequestBody);
        byte[] bytes = baos.toByteArray();

        // 2、创建RPC协议，将Header、Body的内容设置好（Body中存放调用编码）【protocol层】
        RpcRequest rpcRequest = RpcRequest.builder()
                .header("version=1")
                .body(bytes)
                .build();

        // 3、发送RpcRequest，获得RpcResponse
        RpcClientTransfer rpcClientTransfer = new RpcClientTransfer();
        RpcResponse rpcResponse = rpcClientTransfer.sendRequest(rpcRequest);

        // 4、解析RpcResponse，也就是在解析rpc协议【protocol层】
        String header = rpcResponse.getHeader();
        if (header.equals("version=1")) {
            // 将RpcResponse的body中的返回编码，解码成我们需要的对象Object并返回【codec层】
            byte[] body = rpcResponse.getBody();
            ByteArrayInputStream bais = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(bais);
            RpcResponseBody rpcResponseBody = (RpcResponseBody) ois.readObject();
            Object retObject = rpcResponseBody.getRetObject();

            return retObject;
        }

        return null;
    }
}
