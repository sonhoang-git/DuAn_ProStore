package fpoly.sonhaph40315_20_6.duan_prostore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> cartItems;
    private OnCartChangedListener listener;

    public interface OnCartChangedListener {
        void onCartUpdated();
    }

    public CartAdapter(Context context, List<Product> cartItems, OnCartChangedListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);

        // Hiển thị ảnh sản phẩm
        if (product.getImageResId() != 0) {
            holder.imgProduct.setImageResource(product.getImageResId());
        } else {
            holder.imgProduct.setImageResource(R.drawable.ic_kids1); // ảnh mặc định
        }

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.format("%,.0f VND", product.getPrice()));
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));
        holder.tvSize.setText("Size: " + product.getSize());

        // Nút tăng số lượng
        holder.btnIncrease.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < cartItems.size()) {
                Product p = cartItems.get(pos);
                p.setQuantity(p.getQuantity() + 1);
                notifyItemChanged(pos);
                if (listener != null) listener.onCartUpdated();
            }
        });

        // Nút giảm số lượng
        holder.btnDecrease.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < cartItems.size()) {
                Product p = cartItems.get(pos);
                if (p.getQuantity() > 1) {
                    p.setQuantity(p.getQuantity() - 1);
                    notifyItemChanged(pos);
                } else {
                    cartItems.remove(pos);
                    notifyItemRemoved(pos);
                }
                if (listener != null) listener.onCartUpdated();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice, tvQuantity, tvSize;
        ImageButton btnIncrease, btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvSize = itemView.findViewById(R.id.tvSize);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
        }
    }
}
