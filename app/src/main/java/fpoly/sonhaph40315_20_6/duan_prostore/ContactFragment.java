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

import fpoly.sonhaph40315_20_6.duan_prostore.Dao.ContactDao;
import fpoly.sonhaph40315_20_6.duan_prostore.Adapter.ContactAdapter;
import fpoly.sonhaph40315_20_6.duan_prostore.Model.Contact;

public class ContactFragment extends Fragment {

    private RecyclerView recyclerContact;
    private ContactAdapter adapter;
    private ContactDao contactDao;

    public ContactFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerContact = view.findViewById(R.id.recyclerContact);
        contactDao = new ContactDao(requireContext());

        List<Contact> contactList = contactDao.getAllContacts();

        adapter = new ContactAdapter(requireContext(), contactList);
        recyclerContact.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerContact.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.setData(contactDao.getAllContacts());
        }
    }
}
