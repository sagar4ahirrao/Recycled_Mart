package custom_adapter;

import java.util.ArrayList;
import java.util.List;

import userbeans.UserBean;
import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	ArrayList arrayList;
	
	Context context;
	private TextView name;
	private TextView mobile;

	public CustomAdapter(Context convertView, ArrayList<UserBean> arrayList) {
		this.arrayList=arrayList;
		System.out.println(arrayList.size());
		context = convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (arrayList.size());
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (arrayList.get(position));
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return (arrayList.size());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(com.example.recyledmart.R.layout.custom_list_view_management, null);
		}

		name = (TextView) convertView.findViewById(com.example.recyledmart.R.id.custom_name);

		mobile = (TextView) convertView.findViewById(com.example.recyledmart.R.id.custom_number);
		
		
		UserBean bean= (UserBean) arrayList.get(position);
		
		name.setText(bean.getName());
		mobile.setText(bean.getMobile());
		
		System.out.println(bean.getName());
		System.out.println(bean.getMobile());

		return convertView;
	}

}
