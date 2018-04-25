package boys.insper.pro.br.morsesmsapp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Utils {
    public static void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showToast2(View.OnClickListener context, String message) {
        Toast toast = Toast.makeText((Context) context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}