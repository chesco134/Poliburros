package org.jcapiz.poliburros.threads;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class HelloWorker {

	private Looper mServiceLooper;
	private ServiceHandler mServiceHandler;
	private Activity activity;
	
	public HelloWorker(Activity activity){
		this.activity = activity;
		HandlerThread thread = new HandlerThread("ServiceStartArguments",
				android.os.Process.THREAD_PRIORITY_BACKGROUND);
		thread.start();
		// Get the HandlerThread's Looper and use it for our Handler
		mServiceLooper = thread.getLooper();
	}
	
	// Handler that receives messages from the thread
	private final class ServiceHandler extends Handler {

		private AttendantHandler attendantHandler;

		public ServiceHandler(Looper looper) {
			super(looper);
		}

		private class AttendantHandler extends Thread {

			public Handler attendantInnerHandler;

			@Override
			public void run() {
				Looper.prepare();
				attendantInnerHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						// What the attendant has to do when requested...
						// socket = (Socket) msg.obj;
						Toast.makeText(activity,"Hello there!! n.n",Toast.LENGTH_SHORT).show();
					}
				};
				Looper.loop();
			}
		}

		@Override
		public void handleMessage(Message msg) {
			// Aquí se forjan nuevos workers.
			try {
				attendantHandler = new AttendantHandler();
				attendantHandler.start();
				//while (true) {
					// Socket socket = server.accept();
					Message attendantMsg = attendantHandler.attendantInnerHandler
							.obtainMessage();
					// attendantMsg.obj = socket;
					attendantHandler.attendantInnerHandler
							.sendMessage(attendantMsg);
				//}
			} catch (Exception e) {

			}
		}
	}
	
	// Implementing the service Thread
	public void doTheThing(){
		// Cuando sea que se necesite ejecutar cierto procesamiento en seungo
		// plano, se manda llamar a éste método.
		mServiceHandler = new ServiceHandler(mServiceLooper);
		// Se pueden agregar parámetros al hilo a través del objeto msg.
		Message msg = mServiceHandler.obtainMessage();
		// Al ejecutar sendMessage, se inicia el procesamiento en paralelo.
		mServiceHandler.sendMessage(msg);
	}
}