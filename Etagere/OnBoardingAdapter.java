package fr.maxime.mygame01.Etagere;

import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.maxime.mygame01.LibraryArrayBitmapDrawingRessources;
import fr.maxime.mygame01.MyObjects.MaximeToolsLayout;
import fr.maxime.mygame01.MyObjects.TransitionClass;
import fr.maxime.mygame01.R;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder> {


    private ArrayList<OnBoardingItemPage> onBoardingItemPageArrayList;

    // ---------------------------------------------------------------------------------------------
    // Constructor :

    public OnBoardingAdapter(ArrayList<OnBoardingItemPage> onBoardingItemPageArrayList) {

        // VERIF VEGETARIAN VEGAN OU SANS PORC :
        this.onBoardingItemPageArrayList = onBoardingItemPageArrayList;
    }

    // ---------------------------------------------------------------------------------------------
    // Override Methods from RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder>

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.etagerefoodselector_container_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItemPageArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItemPageArrayList.size();
    }

    // ---------------------------------------------------------------------------------------------
    // Sous-Classe OnBoardingViewHolder :

    class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitrePage;
        private LinearLayout linearLayoutInScrollView;

        // -----------------------------------------------------------------------------------------
        // Constructor :

        OnboardingViewHolder(@NonNull View itemView) {

            super(itemView);
            textTitrePage = itemView.findViewById(R.id.etagereFoodSelectorContainer_onBoardingTextView);
            linearLayoutInScrollView = itemView.findViewById(R.id.etagereFoodSelectorContainer_onBoardingLinearLayoutinScrollView);
        }

        // -----------------------------------------------------------------------------------------
        // SETTER :

        void setOnBoardingData(OnBoardingItemPage onBoardingItemPage) {

            textTitrePage.setText(onBoardingItemPage.getTitleText());

            affichageTEST(onBoardingItemPage);

        }

        void affichageTEST(OnBoardingItemPage onBoardingItemPage) {
            linearLayoutInScrollView.removeAllViews();

            for (int i = 0; i < onBoardingItemPage.arrayListRepas.size(); i++) {

                // LAYOUT :
                final LinearLayout linearLayout = new LinearLayout(super.itemView.getContext());
                linearLayout.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (super.itemView.getContext(), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                5, 5, 10, 5, Gravity.CENTER, 0));
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setBackgroundResource(R.drawable.button_border_brownlight);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TransitionClass.playEnumSound(TransitionClass.enumSoundSelectorValue.BubbleClick.ordinal());
                        if (TransitionClass.onBoardingAdapterlinearLayoutArrayList.contains(linearLayout)) {
                            linearLayout.setBackgroundResource(R.drawable.button_border_brownlight);
                            TransitionClass.onBoardingAdapterlinearLayoutArrayList.remove(linearLayout);
                        } else {
                            linearLayout.setBackgroundResource(R.drawable.button_border_orangelight);
                            TransitionClass.onBoardingAdapterlinearLayoutArrayList.add(linearLayout);
                        }
                    }
                });

                // IMAGE :
                ImageView image = new ImageView(super.itemView.getContext());
                image.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (super.itemView.getContext(), 300, 300,
                                10, 10, 10, 10, Gravity.CENTER, 0));
                image.setImageResource(onBoardingItemPage.arrayListRepas.get(i).getImage());

                // LayoutVerticalText :

                LinearLayout linearLayoutVerticalText = new LinearLayout(super.itemView.getContext());
                linearLayoutVerticalText.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (super.itemView.getContext(), LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 0, 0, Gravity.CENTER, 0));
                linearLayoutVerticalText.setOrientation(LinearLayout.VERTICAL);

                // TEXT TITRE REPAS:
                TextView textViewTitre = new TextView(super.itemView.getContext());
                textViewTitre.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (super.itemView.getContext(), LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 10, 0, 0, 0));
                textViewTitre.setTextSize(20);
                textViewTitre.setTextColor(super.itemView.getContext().getColor(R.color.marron1));
                textViewTitre.setTypeface(Typeface.create("casual", Typeface.BOLD));
                textViewTitre.setText(onBoardingItemPage.arrayListRepas.get(i).getTitre());

                // TEXT Description REPAS:
                TextView textViewDescription = new TextView(super.itemView.getContext());
                textViewDescription.setLayoutParams(MaximeToolsLayout.setLayoutParams
                        (super.itemView.getContext(), LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                                0, 0, 0, 0, 0, 0));
                textViewDescription.setPadding(5, 5, 5, 5);
                textViewDescription.setTextSize(16);
                textViewDescription.setTextColor(super.itemView.getContext().getColor(R.color.colorMaxPrimaryWHITE));
                Typeface myfont = ResourcesCompat.getFont(super.itemView.getContext(), R.font.courgette);
                textViewDescription.setTypeface(myfont);
                textViewDescription.setText(onBoardingItemPage.arrayListRepas.get(i).getDescription());

                // ADD VIEW in LAYOUT :

                linearLayoutVerticalText.addView(textViewTitre);
                linearLayoutVerticalText.addView(textViewDescription);

                linearLayout.addView(image);
                linearLayout.addView(linearLayoutVerticalText);
                linearLayoutInScrollView.addView(linearLayout);
            }
        }

    }


}
