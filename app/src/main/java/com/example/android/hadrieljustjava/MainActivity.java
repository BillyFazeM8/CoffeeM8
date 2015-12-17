package com.example.android.hadrieljustjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has Whipped cream" + hasWhippedCream);

        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has Chocolate" + hasChocolate);

        CheckBox BobapearlsCheckBox = (CheckBox) findViewById(R.id.Bobapearls_checkbox);
        boolean hasBobapearls = BobapearlsCheckBox.isChecked();
        Log.v("MainActivity", "Has Boba Pearls" + hasBobapearls);

        CheckBox CaramelCheckBox = (CheckBox) findViewById(R.id.Caramel_checkbox);
        boolean hasCaramel = CaramelCheckBox.isChecked();
        Log.v("MainActivity", "Has Caramel" + hasCaramel);

        int price = calculatePrice(hasWhippedCream, hasChocolate, hasBobapearls, hasCaramel);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate, hasBobapearls, hasCaramel);
        priceMessage = (priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate, boolean addBobapearls, boolean addCaramel) {
        int basePrice = 5;

        if (addWhippedCream){
            basePrice = basePrice + 1;
        }

        if (addChocolate){
                basePrice = basePrice + 2;

        }
        if (addBobapearls) {
            basePrice = basePrice + 2;
        }

        if (addCaramel) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate, boolean addBobapearls, boolean addCaramel ) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream: " + addWhippedCream;
        priceMessage += "\nAdd chocolate: " + addChocolate;
        priceMessage += "\nAdd boba pearls: " + addBobapearls;
        priceMessage += "\nAdd caramel: " + addCaramel;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $ " + price;
        priceMessage += "\nThank you!";
        return  priceMessage;
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    //private void displayMessage(String message) {
      //  TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
       // orderSummaryTextView.setText(message);
    //}
}