package com.example.mauricio.hackatonapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mauricio.hackatonapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private Spinner spinner_land,spinner_tema;
    private TextView nombre,land,tema;
    private EditText nombre_edit;
    private ImageButton boton_foto;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombre = v.findViewById(R.id.txt_nombre);
        land = v.findViewById(R.id.txt_land);
        tema = v.findViewById(R.id.txt_temas);
        nombre_edit = v.findViewById(R.id.edit_nombre);
        boton_foto = v.findViewById(R.id.pic_btn);

        spinner_land = (Spinner) v.findViewById(R.id.spin_land);
        ArrayAdapter<CharSequence> adapter_land = ArrayAdapter.createFromResource(getContext(),
                R.array.lands_array, android.R.layout.simple_spinner_item);
        adapter_land.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_land.setAdapter(adapter_land);

        spinner_tema = (Spinner) v.findViewById(R.id.spin_tema);
        ArrayAdapter<CharSequence> adapter_tema = ArrayAdapter.createFromResource(getContext(),
                R.array.temas_array, android.R.layout.simple_spinner_item);
        adapter_tema.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tema.setAdapter(adapter_tema);

        return v;
    }

}
