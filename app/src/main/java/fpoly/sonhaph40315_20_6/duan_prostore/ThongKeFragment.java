package fpoly.sonhaph40315_20_6.duan_prostore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ThongKeFragment extends Fragment {

    private TextView tvDateRange, tvRevenue, tvOrderCount;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);


        tvDateRange = view.findViewById(R.id.tvDateRange);
        tvRevenue = view.findViewById(R.id.tvRevenue);
        tvOrderCount = view.findViewById(R.id.tvOrderCount);




        tvDateRange.setText("10/7 - 26/7");
        tvRevenue.setText("8.200.000 VND");
        tvOrderCount.setText("1000");



        return view;
    }
}
