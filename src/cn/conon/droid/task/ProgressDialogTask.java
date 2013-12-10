package cn.conon.droid.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 
 * 
 * @param <Params>
 * @param <Progress>
 * @param <Result>
 * @author DDC
 * @date 2013-9-7 下午11:49:18
 * @param <Params>
 * @param <Progress>
 * @param <Result>
 */
public abstract class ProgressDialogTask<Params, Progress, Result> extends
		AsyncTask<Params, Progress, Result> {

	protected Context context;
	private final ProgressDialog dialog;
	private final String message;

	public ProgressDialogTask(final Context context) {
		this(context, null);
	}

	public ProgressDialogTask(final Context context, final String message) {
		this.context = context;
		dialog = new ProgressDialog(context);
		dialog.setCancelable(false);
		this.message = message;
	}

	@Override
	protected void onPreExecute() {
		if (message != null) {
			dialog.setMessage(message);
		}
		dialog.show();
	}

	@Override
	protected void onPostExecute(final Result result) {
		try {
			dialog.dismiss();
		} catch (final Exception e) {
		}
	}

}
