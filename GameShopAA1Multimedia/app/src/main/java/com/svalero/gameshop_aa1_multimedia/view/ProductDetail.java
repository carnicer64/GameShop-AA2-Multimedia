package com.svalero.gameshop_aa1_multimedia.view;

import static com.svalero.gameshop_aa1_multimedia.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.gameshop_aa1_multimedia.R;
import com.svalero.gameshop_aa1_multimedia.contract.product.ProductDetailsContract;
import com.svalero.gameshop_aa1_multimedia.db.GameShopDatabase;
import com.svalero.gameshop_aa1_multimedia.domain.Client;
import com.svalero.gameshop_aa1_multimedia.domain.Favourite;
import com.svalero.gameshop_aa1_multimedia.domain.Product;
import com.svalero.gameshop_aa1_multimedia.presenter.product.ProductDetailsPresenter;

import java.io.File;
import java.util.List;

public class ProductDetail extends AppCompatActivity implements ProductDetailsContract.View {

    private TextView name;
    private TextView cost;
    private TextView sale;
    private TextView barCode;
    private ImageView image;
    private CheckBox favourite;
    private Product detailProduct;
    private String username;
    private Long id;
    private ProductDetailsPresenter productDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intentFrom = getIntent();
        username = intentFrom.getStringExtra("clientUsername");
        id = intentFrom.getLongExtra("client_id", 0L);
        detailProduct = (Product) intentFrom.getSerializableExtra("detail");

        name = findViewById(R.id.productDetName);
        cost = findViewById(R.id.productDetCost);
        sale = findViewById(R.id.productDetSale);
        barCode = findViewById(R.id.productDetBarCode);
        image = findViewById(R.id.productDetImage);
        favourite = findViewById(R.id.detailFavourite);

        /*
        name.setText(detailProduct.getName());
        cost.setText(String.valueOf(detailProduct.getCost()));
        sale.setText(String.valueOf(detailProduct.getSale()));
        barCode.setText(String.valueOf(detailProduct.getBarCode()));

        if(detailProduct.getImageURL() != null) {
            Uri imageUri = Uri.fromFile(new File(detailProduct.getImageURL()));
            image.setImageURI(imageUri);
        }
         */

        productDetailsPresenter = new ProductDetailsPresenter(this);
        productDetailsPresenter.loadDetailsProduct(detailProduct.getId());
        favourite.setOnClickListener(v -> setFavourite());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.menuMain).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.menuProfile){
            final GameShopDatabase db = Room.databaseBuilder(this, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            Client client = db.getClientDAO().getClientById(id);

            Intent intent = new Intent(this, ClientDetails.class);
            intent.putExtra("detail", client);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            startActivity(intent);

        } else if(item.getItemId() == R.id.menuMain){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menuShops){
            Intent intent = new Intent(this, ManageShopsActivity.class);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menuProducts){
            Intent intent = new Intent(this, ManageProductsActivity.class);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menuUsers){
            Intent intent = new Intent(this, ManageClientsActivity.class);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            startActivity(intent);
        } else if(item.getItemId() == R.id.menuPreferences){
            Intent intent = new Intent(this, Preferences.class);
            intent.putExtra("client_id", id);
            intent.putExtra("clientUsername", username);
            startActivity(intent);
        }
        return false;
    }
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("client_id", id);
        intent.putExtra("clientUsername", username);
        startActivity(intent);
    }

    @Override
    public void showProduct(List<Product> products) {
        detailProduct = products.get(0);
        name.setText(detailProduct.getName());
        cost.setText(String.valueOf(detailProduct.getCost()));
        sale.setText(String.valueOf(detailProduct.getSale()));
        barCode.setText(String.valueOf(detailProduct.getBarCode()));
        if(detailProduct.getImageURL() != null) {
            Uri imageUri = Uri.fromFile(new File(detailProduct.getImageURL()));
            image.setImageURI(imageUri);
        }

        final GameShopDatabase db = Room.databaseBuilder(this, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        try {
            Favourite favourite1 = db.getFavouriteDAO().getFavById(detailProduct.getId(), id);
            if(favourite1 != null){
                favourite.setChecked(true);
            } else {
                favourite.setChecked(false);

            }
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        } finally {
            db.close();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void setFavourite(){
        final GameShopDatabase db = Room.databaseBuilder(this, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        try {
            if(favourite.isChecked()){
                Favourite favourite1 = new Favourite(detailProduct.getId(), id, detailProduct.getName(), detailProduct.getBarCode());
                db.getFavouriteDAO().insert(favourite1);
            } else {
                Favourite favourite1 = new Favourite(detailProduct.getId(), id, detailProduct.getName(), detailProduct.getBarCode());
                db.getFavouriteDAO().delete(favourite1);
            }
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        } finally {
            db.close();
        }
    }


}