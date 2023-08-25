package com.svalero.gameshop_aa1_multimedia.adapter;

import static com.svalero.gameshop_aa1_multimedia.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.gameshop_aa1_multimedia.R;
import com.svalero.gameshop_aa1_multimedia.contract.shop.ShopDeleteContract;
import com.svalero.gameshop_aa1_multimedia.presenter.shop.ShopDeletePresenter;
import com.svalero.gameshop_aa1_multimedia.view.RegisterShopActivity;
import com.svalero.gameshop_aa1_multimedia.view.ShopDetails;
import com.svalero.gameshop_aa1_multimedia.db.GameShopDatabase;
import com.svalero.gameshop_aa1_multimedia.domain.Shop;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.SuperheroHolder> implements ShopDeleteContract.View {

    public Context context;
    public List<Shop> shopList;
    private ShopDeletePresenter shopDeletePresenter;

    Intent intentFrom;

    public ShopAdapter(Context context, List<Shop> shopList, Intent intentForm) {
        this.context = context;
        this.shopList = shopList;
        this.intentFrom = intentForm;
        shopDeletePresenter = new ShopDeletePresenter(this);
    }

    public SuperheroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new SuperheroHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.SuperheroHolder holder, int position) {
        holder.name.setText(shopList.get(position).getName());
        holder.adress.setText(shopList.get(position).getAdress());
        holder.tlf.setText(shopList.get(position).getTlf());
        Log.d("onBindViewHolder", String.valueOf(shopList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    @Override
    public void showDeleteShop(String success) {
        Toast.makeText(this.context, success, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this.context, error, Toast.LENGTH_LONG).show();
    }

    public class SuperheroHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView adress;
        public TextView tlf;
        public Button details;
        public Button edit;
        public Button delete;
        public View parentView;

        public SuperheroHolder(View view){
            super(view);
            parentView = view;

            name = view.findViewById(R.id.shopItemName);
            adress = view.findViewById(R.id.shopItemAdress);
            tlf = view.findViewById(R.id.shopItemTlf);
            details = view.findViewById(R.id.shopItemDetails);
            edit = view.findViewById(R.id.shopItemEdit);
            delete = view.findViewById(R.id.shopItemDelete);

            details. setOnClickListener(V -> detailsShop(getAdapterPosition()));
            edit.setOnClickListener(V -> editShop(getAdapterPosition()));
            delete.setOnClickListener(V -> deleteShop(getAdapterPosition()));
        }

        public void deleteShop(int position){
            AlertDialog.Builder delete = new AlertDialog.Builder(context);
            delete.setMessage(R.string.sure).setTitle(R.string.shopDelete)
                    .setPositiveButton(R.string.yes, (dialog, id) -> {
                        //final GameShopDatabase db =  Room.databaseBuilder(context, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
                        Shop shop = shopList.get(position);
                        //db.getShopDAO().delete(shop);
                        shopDeletePresenter.deleteShop(shop.getId());
                        shopList.remove(shop);
                        notifyItemRemoved(position);
                    } ).setNegativeButton(R.string.no, (dialog, id) ->{
                        dialog.dismiss();
                    } );
            AlertDialog dialog = delete.create();
            dialog.show();
        }

        public void editShop(int position){
            Shop shop = shopList.get(position);
            Intent intent = new Intent(context, RegisterShopActivity.class);
            String username = intentFrom.getStringExtra("username");
            Long id = intentFrom.getLongExtra("client_id", 0L);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            intent.putExtra("edit", shop);
            context.startActivity(intent);
        }

        public void detailsShop(int position){
            Shop shop = shopList.get(position);
            Intent intent = new Intent(context, ShopDetails.class);
            String username = intentFrom.getStringExtra("username");
            Long id = intentFrom.getLongExtra("client_id", 0L);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            intent.putExtra("detail", shop);
            context.startActivity(intent);
        }
    }
}
