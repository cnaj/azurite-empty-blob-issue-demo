# Demo project for Azurite issue

This project serves to demonstrate an issue of [Azurite][1] 3.32.0 with empty blobs.

Set the environment variable `AZURE_STORAGE_CONNECTION_STRING` to your Azurite or Azure connection string and execute
the tests in this project. The following results were observed:

* Azurite 3.32.0:

```
Cannot invoke "String.indexOf(int)" because "contentRange" is null
java.lang.NullPointerException: Cannot invoke "String.indexOf(int)" because "contentRange" is null
	at com.azure.storage.blob.implementation.util.ChunkedDownloadUtils.extractTotalBlobLength(ChunkedDownloadUtils.java:131)
	at com.azure.storage.blob.implementation.util.ChunkedDownloadUtils.lambda$downloadFirstChunk$2(ChunkedDownloadUtils.java:75)
	at reactor.core.publisher.Mono.lambda$onErrorResume$32(Mono.java:3887)
	at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:94)
	at reactor.core.publisher.MonoFlatMap$FlatMapMain.onError(MonoFlatMap.java:172)
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onError(FluxMapFuseable.java:142)
	at reactor.core.publisher.MonoFlatMap$FlatMapMain.onError(MonoFlatMap.java:172)
	at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondError(MonoFlatMap.java:192)
	at reactor.core.publisher.MonoFlatMap$FlatMapInner.onError(MonoFlatMap.java:259)
	at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:142)
	at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onNext(FluxSwitchIfEmpty.java:74)
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:129)
	at reactor.core.publisher.MonoUsing$MonoUsingSubscriber.onNext(MonoUsing.java:231)
	at reactor.core.publisher.FluxHandle$HandleSubscriber.onNext(FluxHandle.java:126)
	at reactor.core.publisher.FluxMap$MapConditionalSubscriber.onNext(FluxMap.java:224)
	at reactor.core.publisher.FluxDoFinally$DoFinallySubscriber.onNext(FluxDoFinally.java:113)
	at reactor.core.publisher.FluxHandleFuseable$HandleFuseableSubscriber.onNext(FluxHandleFuseable.java:191)
	at reactor.core.publisher.FluxContextWrite$ContextWriteSubscriber.onNext(FluxContextWrite.java:107)
	at reactor.core.publisher.Operators$MonoSubscriber.complete(Operators.java:1839)
	at reactor.core.publisher.MonoCollectList$MonoCollectListSubscriber.onComplete(MonoCollectList.java:129)
	at reactor.core.publisher.FluxPeek$PeekSubscriber.onComplete(FluxPeek.java:260)
	at reactor.core.publisher.FluxMap$MapSubscriber.onComplete(FluxMap.java:144)
	at reactor.netty.channel.FluxReceive.onInboundComplete(FluxReceive.java:415)
	at reactor.netty.channel.ChannelOperations.onInboundComplete(ChannelOperations.java:439)
	at reactor.netty.channel.ChannelOperations.terminate(ChannelOperations.java:493)
	at reactor.netty.http.client.HttpClientOperations.onInboundNext(HttpClientOperations.java:789)
	at reactor.netty.channel.ChannelOperationsHandler.channelRead(ChannelOperationsHandler.java:114)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:444)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412)
	at com.azure.core.http.netty.implementation.AzureSdkHandler.channelRead(AzureSdkHandler.java:224)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:442)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412)
	at io.netty.channel.CombinedChannelDuplexHandler$DelegatingChannelHandlerContext.fireChannelRead(CombinedChannelDuplexHandler.java:436)
	at io.netty.handler.codec.ByteToMessageDecoder.fireChannelRead(ByteToMessageDecoder.java:346)
	at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:318)
	at io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:251)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:442)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:412)
	at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1407)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:440)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:420)
	at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:918)
	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:166)
	at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:788)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:724)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:650)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:562)
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:994)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:840)
	Suppressed: com.azure.storage.blob.models.BlobStorageException: Status code 416, "<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Error>
  <Code>InvalidRange</Code>
  <Message>The range specified is invalid for the current size of the resource.
RequestId:b310ae61-f4c6-4740-a8ab-f4c328ec46c7
Time:2024-08-30T09:37:05.375Z</Message>
</Error>"
		at java.base/java.lang.invoke.MethodHandle.invokeWithArguments(MethodHandle.java:732)
		at com.azure.core.implementation.MethodHandleReflectiveInvoker.invokeStatic(MethodHandleReflectiveInvoker.java:26)
		at com.azure.core.implementation.http.rest.ResponseExceptionConstructorCache.invoke(ResponseExceptionConstructorCache.java:53)
		at com.azure.core.implementation.http.rest.RestProxyBase.instantiateUnexpectedException(RestProxyBase.java:407)
		at com.azure.core.implementation.http.rest.AsyncRestProxy.lambda$ensureExpectedStatus$1(AsyncRestProxy.java:135)
		at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:113)
		... 42 more
	Suppressed: java.lang.Exception: #block terminated with an error
		at reactor.core.publisher.BlockingSingleSubscriber.blockingGet(BlockingSingleSubscriber.java:100)
		at reactor.core.publisher.Mono.block(Mono.java:1742)
		at com.azure.storage.blob.specialized.BlobClientBase.openInputStream(BlobClientBase.java:393)
		at com.azure.storage.blob.specialized.BlobClientBase.openInputStream(BlobClientBase.java:323)
		at com.azure.storage.blob.specialized.BlobClientBase.openInputStream(BlobClientBase.java:312)
		at com.azure.storage.blob.specialized.BlobClientBase.openInputStream(BlobClientBase.java:299)
		at com.example.demo.AzuriteEmptyBlobTest.testEmptyBlob(AzuriteEmptyBlobTest.java:48)
		at java.base/java.lang.reflect.Method.invoke(Method.java:569)
		at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
		at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
```

* Azurite 3.31.0: No problem
* Azure Blob Storage: No problem

**Note:** Gradle apparently doesn't log or report suppressed exceptions. The stack trace above was captured with
IntelliJ IDEA.

[1]: https://github.com/Azure/Azurite
