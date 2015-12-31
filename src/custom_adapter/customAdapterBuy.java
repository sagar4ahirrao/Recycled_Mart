package custom_adapter;

import java.util.ArrayList;
import java.util.List;

import com.db.DbHelper;

import userbeans.ProductBean;
import userbeans.UserBean;
import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapterBuy extends BaseAdapter {

	ArrayList arrayListName;

	ArrayList arrayListPrice;
	ArrayList arrImageName = new ArrayList();
	Context context;
	private TextView name;
	private TextView mobile;

	private TextView price;

	private ImageView imageView;

	public customAdapterBuy(Context convertView, ArrayList arrName, ArrayList arrprice) {
		arrayListName = arrName;
		arrayListPrice = arrprice;
		context = convertView;
		System.out.println(arrayListName.size());
		arrImageName.add(com.example.recyledmart.R.drawable.product_1);
		arrImageName.add(com.example.recyledmart.R.drawable.product_2);
		arrImageName.add(com.example.recyledmart.R.drawable.product_3);
		arrImageName.add(com.example.recyledmart.R.drawable.product_4);
		arrImageName.add(com.example.recyledmart.R.drawable.product_5);
		arrImageName.add(com.example.recyledmart.R.drawable.product_6);
		arrImageName.add(com.example.recyledmart.R.drawable.product_7);
		arrImageName.add(com.example.recyledmart.R.drawable.product_8);
		arrImageName.add(com.example.recyledmart.R.drawable.product_9);
		arrImageName.add(com.example.recyledmart.R.drawable.product_10);
		arrImageName.add(com.example.recyledmart.R.drawable.product_11);
		arrImageName.add(com.example.recyledmart.R.drawable.product_12);
		
		ArrayList newlist = new ArrayList();
		DbHelper helper = new DbHelper(context);
		ProductBean bean = new ProductBean();
		newlist = helper.get_product_details();
		for (int i = 0; i < newlist.size(); i++) 
		{
			
			bean = (ProductBean) newlist.get(i);
			System.out.println(bean.getImage_path());
			arrayListName.add(bean.getProduct_name());
			arrayListPrice.add(bean.getProduct_price());
			arrImageName.add(bean.getImage_path());
			
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (arrayListName.size());
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (arrayListName.get(position));
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return (arrayListName.size());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(com.example.recyledmart.R.layout.custom_buy, null);
		}

		imageView = (ImageView) convertView.findViewById(com.example.recyledmart.R.id.buy_image_id);
		name = (TextView) convertView.findViewById(com.example.recyledmart.R.id.product_name);

		price = (TextView) convertView.findViewById(com.example.recyledmart.R.id.product_price);

		name.setText(arrayListName.get(position).toString());
		price.setText(arrayListPrice.get(position).toString());
		
		try
		{
			imageView.setImageResource((Integer) arrImageName.get(position));
		}
		catch(Exception e)
		{
		
			System.out.println("\n\nex");
			System.out.println(arrImageName.get(position));
			Bitmap bitmap = BitmapFactory.decodeFile(arrImageName.get(position).toString());
			imageView.setImageBitmap(bitmap);
			
		}
		

		return convertView;
	}

}
