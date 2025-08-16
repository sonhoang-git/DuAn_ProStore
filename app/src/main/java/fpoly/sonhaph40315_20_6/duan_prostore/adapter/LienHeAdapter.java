package fpoly.sonhaph40315_20_6.duan_prostore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.R;
import fpoly.sonhaph40315_20_6.duan_prostore.model.LienHe_Model;

public class LienHeAdapter extends RecyclerView.Adapter<LienHeAdapter.ContactViewHolder> {
    private Context context;
    private List<LienHe_Model> contactList;

    public LienHeAdapter(Context context, List<LienHe_Model> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lien_he, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        LienHe_Model contact = contactList.get(position);
        holder.tvName.setText("Họ tên: " + contact.getFullname());
        holder.tvPhone.setText("SĐT: " + contact.getPhone());
        holder.tvEmail.setText("Email: " + contact.getEmail());
        holder.tvAddress.setText("Địa chỉ: " + contact.getAddress());
        holder.tvMessage.setText("Nội dung: " + contact.getNoidung());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvEmail, tvAddress, tvMessage, tvDate;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    public void setData(List<LienHe_Model> newList) {
        this.contactList = newList;
        notifyDataSetChanged();
    }
}
