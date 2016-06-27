package ucai.cn.mishi.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import ucai.cn.mishi.R;
import ucai.cn.mishi.fragment.DiscoveryFragment;
import ucai.cn.mishi.fragment.HomeFragment;
import ucai.cn.mishi.fragment.OrderFragment;
import ucai.cn.mishi.fragment.PersonalFragment;

public class MainActivity extends AppCompatActivity {
    private RadioButton[] Radio;
    private int index;
    private int currentTabIndex;
    Fragment[] fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        HomeFragment homeFragment = new HomeFragment();
        OrderFragment orderFragment = new OrderFragment();
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        PersonalFragment personalFragment = new PersonalFragment();
        fragment = new Fragment[]{homeFragment, orderFragment, discoveryFragment, personalFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.fr_quanbu, homeFragment)
                .add(R.id.fr_quanbu, orderFragment).hide(orderFragment)
                .add(R.id.fr_quanbu, discoveryFragment).hide(discoveryFragment)
                .add(R.id.fr_quanbu, personalFragment).hide(personalFragment)
                .show(homeFragment)
                .commit();
    }

    private void initView() {
        Radio = new RadioButton[4];
        Radio[0] = (RadioButton) findViewById(R.id.mishi);
        Radio[1] = (RadioButton) findViewById(R.id.dingdan);
        Radio[2] = (RadioButton) findViewById(R.id.taosuo);
        Radio[3] = (RadioButton) findViewById(R.id.wo);
        registerForContextMenu(Radio[1]);
    }

    public void onCheckedChange(View v) {
        switch (v.getId()) {
            case R.id.mishi:
                index = 0;
                break;
            case R.id.dingdan:
                index = 1;
                break;
            case R.id.taosuo:
                index = 2;
                break;
            case R.id.wo:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragment[currentTabIndex]);
            if (!fragment[index].isAdded()) {
                trx.add(R.id.fr_quanbu, fragment[index]);
            }
            trx.show(fragment[index]).commit();
            Radio[currentTabIndex].setChecked(false);
            // 把当前tab设为选中状态
            Radio[index].setChecked(true);
            currentTabIndex = index;
        }
    }
}
