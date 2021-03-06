package casia.isiteam.videosearch.master;

import casia.isiteam.videosearch.protocol.Protocol;
import casia.isiteam.videosearch.protocol.Protocol.Request;
import casia.isiteam.videosearch.protocol.Protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SlaveResponseHandler extends SimpleChannelInboundHandler<Protocol.Response> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Response msg)
			throws Exception {
		// TODO Auto-generated method stub


			switch (msg.getHead().getMethodType()) {
			
			case ADD_VIDEO:
			case DELETE_VIDEO:
				dispatchResponse(ctx,msg);
				break;
			case SEARCH_VIDEO:
				mergeResponse(ctx, msg);
				break;
			default:
				break;

			}
		
		
	}
	private void dispatchResponse(ChannelHandlerContext ctx, Response msg){
		
	}
	
	private void mergeResponse(ChannelHandlerContext ctx, Response msg){
		
	}
}
