package boys.insper.pro.br.morsesmsapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DictAdapter extends BaseAdapter {

//    private final List<String> listLettersAZ;
//    private final List<String> listMorseAZ;
//    private final List<String> listLettersGetCodes;
//    private final List<String> listMorseGetCodes;
    private final List<DictionaryItem> dictionary;
    private final Activity act;

//    public DictAdapter(List<String> listLettersAZ, List<String> listMorseAZ, List<String> listLettersGetCodes, List<String> listMorseGetCodes, Activity act) {
//        this.listLettersAZ = listLettersAZ;
//        this.listMorseAZ = listMorseAZ;
//        this.listLettersGetCodes = listLettersGetCodes;
//        this.listMorseGetCodes = listMorseGetCodes;
//        this.act = act;
//    }

    public DictAdapter(List<DictionaryItem> dictionary, Activity act) {
        this.dictionary = dictionary;
        this.act = act;
    }


    @Override
    public int getCount() {
        return dictionary.size();
    }

    @Override
    public Object getItem(int i) {
        return dictionary.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = act.getLayoutInflater().inflate(R.layout.list_layout, viewGroup, false);

        DictionaryItem dictItem = dictionary.get(i);

//        String item1 = listLettersAZ.get(i);
//        String item2 = listMorseAZ.get(i);
//        String item3 = listLettersGetCodes.get(i);
//        String item4 = listMorseGetCodes.get(i);

        TextView tv1 = view.findViewById(R.id.text1);
        TextView tv2 = view.findViewById(R.id.text2);
        TextView tv3 = view.findViewById(R.id.text3);
        TextView tv4 = view.findViewById(R.id.text4);

        tv1.setText(dictItem.getlAZ());
        tv2.setText(dictItem.getmAZ());
        tv4.setText(dictItem.getlCodes());
        tv3.setText(dictItem.getmCodes());


        return view;
    }
}
