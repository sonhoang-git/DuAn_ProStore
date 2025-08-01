package fpoly.sonhaph40315_20_6.duan_prostore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fpoly.sonhaph40315_20_6.duan_prostore.Adapter.OrderDetailAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.Dao.OrderDetailDao;
import fpoly.sonhaph40315_20_6.duan_prostore.Dao.OrderDetailDao;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.OrderDetail;

public class OrderDetailFragment extends Fragment {
    private RecyclerView rcvOrderDetail;
    private OrderDetailAdapter adapter;
    private List<OrderDetail> detailList;

    private static final String ARG_ORDER_ID = "order_id";
    private int orderId;

    public static OrderDetailFragment newInstance(int orderId) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ORDER_ID, orderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderId = getArguments().getInt(ARG_ORDER_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        rcvOrderDetail = view.findViewById(R.id.rcvOrderDetail);

        // Load dữ liệu từ SQLite bằng DAO
        OrderDetailDao dao = new OrderDetailDao(getContext());
        detailList = dao.getOrderDetailsByOrderId(orderId);

        adapter = new OrderDetailAdapter(getContext(), detailList);
        rcvOrderDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvOrderDetail.setAdapter(adapter);

        return view;
    }
}
