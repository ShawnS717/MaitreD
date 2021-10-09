package init;

import networkRequestTaking.RequestSocketListener;

public class Startup {

	public static void main(String[] args) {
		RequestSocketListener socketListener = new RequestSocketListener(1000,true);
		//10.0.0.225:1000
		socketListener.start();
	}
}
