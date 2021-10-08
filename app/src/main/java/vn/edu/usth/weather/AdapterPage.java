package vn.edu.usth.weather;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public  class AdapterPage extends FragmentPagerAdapter{
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[] {"Hanoi", "Paris", "Toulouse"};
    public AdapterPage(FragmentManager Fm){
        super(Fm);
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int page){
        switch (page){
            case 0:
                return WeatherAndForecastFragment.newInstance();
            case 1:
                return WeatherAndForecastFragment.newInstance();
            case 2:
                return WeatherAndForecastFragment.newInstance();

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int page){
        return titles[page];
    }
}
