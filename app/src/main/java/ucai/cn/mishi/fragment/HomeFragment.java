package ucai.cn.mishi.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ucai.cn.mishi.activity.MainActivity;
import ucai.cn.mishi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    BoutiqueFragment mBoutiqueFragment;
    SnackFragment mSnackFragment;
    ViewPager mViewPager;
    LinearLayout mll_boutique,mll_snack;
    TextView mtv_boutique,mtv_snack;
    View mv_boutique_line,mv_snack_line;
    ImageView miv_location,miv_search;
    MainActivity mContext;
    Fragment[] fragment;
    HomePageAdapter mAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = (MainActivity) getActivity();
        initView(layout);
        initFragment();
        setListener();
        return layout;
    }

    private void setListener() {
        ViewPagerListener();
        BoutiqueListener();
        SnackListener();
    }

    private void SnackListener() {
        mll_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtv_boutique.setTextColor(Color.GRAY);
                mtv_snack.setTextColor(Color.GREEN);
                mv_boutique_line.setVisibility(View.GONE);
                mv_snack_line.setVisibility(View.VISIBLE);
            }
        });
    }

    private void BoutiqueListener() {
        mll_boutique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtv_boutique.setTextColor(Color.GREEN);
                mtv_snack.setTextColor(Color.GRAY);
                mv_boutique_line.setVisibility(View.VISIBLE);
                mv_snack_line.setVisibility(View.GONE);
            }
        });
    }

    private void ViewPagerListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mtv_boutique.setTextColor(Color.GREEN);
                        mtv_snack.setTextColor(Color.GRAY);
                        mv_boutique_line.setVisibility(View.VISIBLE);
                        mv_snack_line.setVisibility(View.GONE);
                        break;
                    case 1:
                        mtv_boutique.setTextColor(Color.GRAY);
                        mtv_snack.setTextColor(Color.GREEN);
                        mv_boutique_line.setVisibility(View.GONE);
                        mv_snack_line.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        mBoutiqueFragment = new BoutiqueFragment();
        mSnackFragment = new SnackFragment();
        fragment = new Fragment[]{mBoutiqueFragment, mSnackFragment};
        mAdapter = new HomePageAdapter(mContext.getSupportFragmentManager(), fragment);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initView(View layout) {
        mViewPager = (ViewPager) layout.findViewById(R.id.fl_home);
        mll_boutique = (LinearLayout) layout.findViewById(R.id.mishijingxuan);
        mll_snack = (LinearLayout) layout.findViewById(R.id.xiaochitiandian);
        mtv_boutique = (TextView) layout.findViewById(R.id.boutique);
        mtv_snack = (TextView) layout.findViewById(R.id.snack);
        mv_boutique_line = layout.findViewById(R.id.boutique_line);
        mv_snack_line = layout.findViewById(R.id.snack_line);
        miv_location = (ImageView) layout.findViewById(R.id.location);
        miv_search = (ImageView) layout.findViewById(R.id.search);
    }

    class HomePageAdapter extends FragmentStatePagerAdapter {
        Fragment[] fragment;
        public HomePageAdapter(FragmentManager fm,Fragment[] fragment) {
            super(fm);
            this.fragment = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            return fragment[position];
        }

        @Override
        public int getCount() {
            return fragment==null?0:fragment.length;
        }
    }
}
