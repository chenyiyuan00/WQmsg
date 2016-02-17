package com.coderqi.publicutil.voice;

import android.content.Context;

import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.myandroid.activity.ChatActivity;

/**
 * 识别回调监听器
 */

public class MyRecognizerDialogLister implements RecognizerDialogListener {
	private final Context context;

	public static StringBuilder words = new StringBuilder();

	public MyRecognizerDialogLister(Context context) {
		this.context = context;
	}

	// 自定义的结果回调函数，成功执行第一个方法，失败执行第二个方法
	@Override
	public void onResult(RecognizerResult results, boolean isLast) {

		String text = JsonParser.parseIatResult(results.getResultString());
		words.append(text);
		Tip.log(text);
		Tip.log(words.toString());
		ChatActivity.chartMsg
				.setText(MyRecognizerDialogLister.words.toString());
		// Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	/**
	 * @see 识别回调错误.
	 */
	@Override
	public void onError(SpeechError error) {
		// TODO Auto-generated method stub
		int errorCoder = error.getErrorCode();
		switch (errorCoder) {
		case 10118:
			System.out.println("user don't speak anything");
			break;
		case 10204:
			System.out.println("can't connect to internet");
			break;
		default:
			break;
		}
	}

}
