# TabLayoutDemo

 参考文档 [http://www.jianshu.com/p/39a66373498c](http://www.jianshu.com/p/39a66373498c)
 
 1. 顶部指示器 代码注释 TopTabLayout
 
        package com.aplex.erasemmc.tablayoutdemo;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewCompat;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;

        import java.util.ArrayList;
        import java.util.List;

        /**
         * Created by chengmz on 2016/10/26.
         */
        public class TopTabLayoutActivity extends AppCompatActivity{
            private TabLayout mTabTl;
            private ViewPager mContentVp;

            private List<String> tabIndicators;
            private List<Fragment> tabFragments;
            private ContentPagerAdapter contentAdapter;

            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.top_tab);

                mTabTl = (TabLayout) findViewById(R.id.tl_tab);
                mContentVp = (ViewPager) findViewById(R.id.vp_content);

                initContent();
                initTab();
            }

            private void initTab(){
                //设置适配模式
                mTabTl.setTabMode(TabLayout.MODE_SCROLLABLE);
                //设置未选择字体颜色，和选中字体颜色
                mTabTl.setTabTextColors(Color.GRAY, Color.RED);
                //设置指示器颜色
                mTabTl.setSelectedTabIndicatorColor(Color.RED);
                //设置z轴 暂时无法看出效果
                ViewCompat.setElevation(mTabTl, 40);
                //和viewpager进行联动
                mTabTl.setupWithViewPager(mContentVp);
            }

            private void initContent(){
                tabIndicators = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    tabIndicators.add("Tab " + i);
                }
                tabFragments = new ArrayList<>();
                for (String s : tabIndicators) {
                    tabFragments.add(TabContentFragment.newInstance(s));
                }
                contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
                mContentVp.setAdapter(contentAdapter);
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                //根据menu文件夹的menu_tab_layout文件的数据进行适配
                getMenuInflater().inflate(R.menu.menu_tab_layout, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_add:
                        //tabIndicators数据集添加数据
                        tabIndicators.add("Tab " + tabIndicators.size());
                        //tabFragments 添加fragment
                        tabFragments.add(TabContentFragment.newInstance(tabIndicators.get(tabIndicators.size()-1)));
                        //notify 让tab viewpager ui 和数据进行适配
                        contentAdapter.notifyDataSetChanged();
                        return true;

                    case R.id.tab_mode_fixed:
                        //fixedMode 模式 所有tab都显示在屏幕上
                        mTabTl.setTabMode(TabLayout.MODE_FIXED);
                        return true;

                    case R.id.tab_mode_scrollable:
                        //scrollableMode 模式 屏幕上只显示特定个数的tab
                        mTabTl.setTabMode(TabLayout.MODE_SCROLLABLE);
                        break;
                }
                return super.onOptionsItemSelected(item);
            }

            class ContentPagerAdapter extends FragmentPagerAdapter {
                public ContentPagerAdapter(FragmentManager fm) {
                    super(fm);
                }

                @Override
                public Fragment getItem(int position) {
                    return tabFragments.get(position);
                }

                @Override
                public int getCount() {
                    return tabIndicators.size();
                }

                //TabLayout 会到根据getPageTitle取值适配
                @Override
                public CharSequence getPageTitle(int position) {
                    return tabIndicators.get(position);
                }
            }
        }
        
 1. 底部指示器 代码注释 BottomTabLayoutActivity
 
        package com.aplex.erasemmc.tablayoutdemo;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewCompat;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

        /**
         * Created by chengmz on 2016/10/26.
         */
        public class BottomTabLayoutActivity extends AppCompatActivity{
            private TabLayout mTabTl;
            private ViewPager mContentVp;

            private List<String> tabIndicators;
            private List<Fragment> tabFragments;
            private ContentPagerAdapter contentAdapter;

            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.bottom_tab);
                mTabTl = (TabLayout) findViewById(R.id.tl_tab);
                mContentVp = (ViewPager) findViewById(R.id.vp_content);
                initContent();
                initTab();
            }

            private void initTab(){
                //设置模式  为屏幕显示固定个数
                mTabTl.setTabMode(TabLayout.MODE_FIXED);
                //设置指示器高度未0  就是隐藏指示器
                mTabTl.setSelectedTabIndicatorHeight(0);
                ViewCompat.setElevation(mTabTl, 10);
                //设置和viewpager联动
                mTabTl.setupWithViewPager(mContentVp);
                for (int i = 0; i < tabIndicators.size(); i++) {
                    TabLayout.Tab itemTab = mTabTl.getTabAt(i);
                    if (itemTab!=null){
                        itemTab.setCustomView(R.layout.item_tab_layout_custom);
                        TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                        itemTv.setText(tabIndicators.get(i));
                    }
                }
                mTabTl.getTabAt(0).getCustomView().setSelected(true);
            }

            private void initContent(){
                tabIndicators = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    tabIndicators.add("Tab " + i);
                }
                tabFragments = new ArrayList<>();
                for (String s : tabIndicators) {
                    tabFragments.add(TabContentFragment.newInstance(s));
                }
                contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
                mContentVp.setAdapter(contentAdapter);
            }

            class ContentPagerAdapter extends FragmentPagerAdapter {

                public ContentPagerAdapter(FragmentManager fm) {
                    super(fm);
                }

                @Override
                public Fragment getItem(int position) {
                    return tabFragments.get(position);
                }

                @Override
                public int getCount() {
                    return tabIndicators.size();
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return tabIndicators.get(position);
                }
            }
        }

 
 
