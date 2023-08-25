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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.gameshop_aa1_multimedia.contract.product.ProductDeleteContract;
import com.svalero.gameshop_aa1_multimedia.presenter.product.ProductDeletePresenter;
import com.svalero.gameshop_aa1_multimedia.view.ProductDetail;
import com.svalero.gameshop_aa1_multimedia.view.RegisterProductActivity;
import com.svalero.gameshop_aa1_multimedia.db.GameShopDatabase;
import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.SuperheroHolder> implements ProductDeleteContract.View, Filterable {
    public Context context;
    public List<Product> productList;
    private ProductDeletePresenter productDeletePresenter;
    Intent intentFrom;

    public ProductAdapter(Context context, List<Product> productList, Intent intentFrom) {
        this.context = context;
        this.productList = productList;
        this.intentFrom = intentFrom;
        productDeletePresenter = new ProductDeletePresenter(this);
    }


    @NonNull
    @Override
    public SuperheroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new SuperheroHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.SuperheroHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(String.valueOf(product.getName()));
        holder.barCode.setText(String.valueOf(product.getBarCode()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void showDeleteProduct(String success) {
        Toast.makeText(this.context, success, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showDeleteError(String error) {
        Toast.makeText(this.context, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public Filter getFilter() {
        return new ProductAdapterFilter();
    }

    public class SuperheroHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView barCode;
        public Button details;
        public Button edit;
        public Button delete;
        public View parentView;

        public SuperheroHolder(View view){
            super(view);
            parentView = view;
            name = view.findViewById(R.id.productItemName);
            barCode = view.findViewById(R.id.productItemBarCode);
            details = view.findViewById(R.id.productItemDetail);
            edit = view.findViewById(R.id.productItemEdit);
            delete = view.findViewById(R.id.productItemDelete);

            details.setOnClickListener(V -> detailsProduct(getAdapterPosition()));
            edit.setOnClickListener(V -> editProduct(getAdapterPosition()));
            delete.setOnClickListener(v -> deleteProduct(getAdapterPosition()));
        }

        public void deleteProduct(int position){
            AlertDialog.Builder delete = new AlertDialog.Builder(context);
            delete.setMessage(R.string.sure).setTitle(R.string.productDelete)
                    .setPositiveButton(R.string.yes, (dialog, id) -> {
                        //final GameShopDatabase db = Room.databaseBuilder(context, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
                        Product product = productList.get(position);
                        //db.getProductDAO().delete(product);
                        productDeletePresenter.deleteProduct(product.getId());
                        productList.remove(product);
                        notifyItemRemoved(position);
                    }).setNegativeButton(R.string.no, (dialog, id) -> {
                        dialog.dismiss();
                    });
            AlertDialog dialog = delete.create();
            dialog.show();
        }

        public void editProduct(int position){
            Product product = productList.get(position);
            Intent intent = new Intent(context, RegisterProductActivity.class);
            String username = intentFrom.getStringExtra("username");
            Long id = intentFrom.getLongExtra("client_id", 0L);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            intent.putExtra("edit", product);
            context.startActivity(intent);
        }

        public void detailsProduct(int position){
            Product product = productList.get(position);
            Intent intent = new Intent(context, ProductDetail.class);
            String username = intentFrom.getStringExtra("username");
            Long id = intentFrom.getLongExtra("client_id", 0L);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            intent.putExtra("detail", product);
            context.startActivity(intent);
        }
    }

    class ProductAdapterFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Product product : productList) {
                    if (product.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(product);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    }
}
