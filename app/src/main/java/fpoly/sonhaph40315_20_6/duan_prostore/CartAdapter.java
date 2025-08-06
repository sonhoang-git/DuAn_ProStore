    // CartAdapter.java
    package fpoly.sonhaph40315_20_6.duan_prostore;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.List;

    import fpoly.sonhaph40315_20_6.duan_prostore.dao.GioHang_Dao;

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

        public interface OnCartChangedListener {
            void onCartUpdated();
        }

        private Context context;
        private List<Product> cartItems;
        private OnCartChangedListener listener;
        private GioHang_Dao gioHang_dao;
        public CartAdapter(Context context, List<Product> cartItems, OnCartChangedListener listener) {
            this.context = context;
            this.cartItems = cartItems;
            this.listener = listener;
            this.gioHang_dao = new GioHang_Dao(context);
        }

        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
            return new CartViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
            Product item = cartItems.get(position);

            holder.imgProduct.setImageResource(item.getImageResId());
            holder.tvName.setText(item.getName());
            holder.tvPrice.setText(item.getPrice());
            holder.tvSize.setText("Size: " + item.getSize());
            holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

            holder.btnMinus.setOnClickListener(v -> {

                Product items = cartItems.get(position);
                if (items.getQuantity() > 1) {
                    items.setQuantity(items.getQuantity() - 1);
                    gioHang_dao.updateQuantity(items.getName(), items.getSize(), items.getQuantity());

                    holder.tvQuantity.setText(String.valueOf(items.getQuantity()));
                    listener.onCartUpdated();
                } else {
                    gioHang_dao.remove_GioHang(items);  // Xóa bằng name + size
                    cartItems.remove(position);
                    notifyItemRemoved(position);
                    listener.onCartUpdated();
                }
            });

            holder.btnPlus.setOnClickListener(v -> {

                Product items = cartItems.get(position);
                items.setQuantity(items.getQuantity() + 1);
                gioHang_dao.updateQuantity(items.getName(), items.getSize(), items.getQuantity());

                holder.tvQuantity.setText(String.valueOf(items.getQuantity()));
                listener.onCartUpdated();
            });
        }

        @Override
        public int getItemCount() {
            return cartItems.size();
        }

        public static class CartViewHolder extends RecyclerView.ViewHolder {
            ImageView imgProduct, btnMinus, btnPlus;
            TextView tvName, tvPrice, tvQuantity, tvSize;

            public CartViewHolder(@NonNull View itemView) {
                super(itemView);
                imgProduct = itemView.findViewById(R.id.imgCartProduct);
                tvName = itemView.findViewById(R.id.tvCartName);
                tvPrice = itemView.findViewById(R.id.tvCartPrice);
                tvQuantity = itemView.findViewById(R.id.tvQuantity);
                tvSize = itemView.findViewById(R.id.tvCartSize);
                btnMinus = itemView.findViewById(R.id.btnMinus);
                btnPlus = itemView.findViewById(R.id.btnPlus);
            }
        }
        public void updateData(List<Product> newList) {
            this.cartItems.clear();
            this.cartItems.addAll(newList);
            notifyDataSetChanged();
        }
    }
