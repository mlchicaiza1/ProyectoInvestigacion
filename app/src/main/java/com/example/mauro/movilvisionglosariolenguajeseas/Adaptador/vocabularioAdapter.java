package com.example.mauro.movilvisionglosariolenguajeseas.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.model.vocabularioClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauro on 22/10/2018.
 */

public class vocabularioAdapter extends RecyclerView.Adapter<vocabularioAdapter.myViewHolder> implements Filterable,View.OnClickListener {


    Context mContext;
   private List<vocabularioClass> mData;
   private ArrayList<vocabularioClass> vocabularioFliter;
   private CustomFilter mFilter;
   private View.OnClickListener listener;




    public vocabularioAdapter(Context mContext, List<vocabularioClass> mData) {
        this.mContext = mContext;
        this.mData = mData;

        this.vocabularioFliter =new ArrayList<>();
        this.vocabularioFliter.addAll(mData);
        this.mFilter=new CustomFilter(vocabularioAdapter.this);
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(mContext);
        View v=inflater.inflate(R.layout.card_item,parent,false);

        v.setOnClickListener(this);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

      holder.imgFondoPalabra.setImageResource(vocabularioFliter.get(position).getFondoletra());
      holder.imgLetraPalabra.setImageResource(vocabularioFliter.get(position).getImagen1());
      holder.txtLetra.setText(vocabularioFliter.get(position).getLetra());
      holder.txtPalabraGlosario.setText(vocabularioFliter.get(position).getPalabra());

    }

    @Override
    public int getItemCount() {
        return vocabularioFliter.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imgLetraPalabra,imgFondoPalabra;
        TextView txtLetra,txtPalabraGlosario;

        public myViewHolder(View itemView){
            super(itemView);
            imgLetraPalabra=itemView.findViewById(R.id.imgLetra);
            imgFondoPalabra=itemView.findViewById(R.id.imgFondo);
            txtLetra=itemView.findViewById(R.id.txtletra);
            txtPalabraGlosario=itemView.findViewById(R.id.txtPalabra);
        }
    }

    public class CustomFilter extends Filter{

        private vocabularioAdapter vocAdapter;

        public CustomFilter(vocabularioAdapter listAdapter){
            super();
            this.vocAdapter=listAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            vocabularioFliter.clear();
            final FilterResults results =new FilterResults();
            if(constraint.length()==0){
                vocabularioFliter.addAll(mData);

            }else{
                final String filterPattern=constraint.toString().toLowerCase().trim();
                for (final vocabularioClass vocabulario: mData){
                    if(vocabulario.getPalabra().toLowerCase().contains(filterPattern)){
                        vocabularioFliter.add(vocabulario);
                    }
                }
            }
            results.values=vocabularioFliter;
            results.count=vocabularioFliter.size();
            return  results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            this.vocAdapter.notifyDataSetChanged();
        }
    }


}


