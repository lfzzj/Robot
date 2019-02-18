package com.hamitao.kids.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hamitao.framework.constant.BaseConstant;
import com.hamitao.kids.R;
import com.hamitao.kids.model.FollowMeBean;
import com.hamitao.kids.model.FuncBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @data on 2018/5/29 9:59
 * @describe: 数据
 */

public class DataFuncUtil {

    /**
     * 获取功能数据
     *
     * @param context
     * @return
     */
    public static List<FuncBean> getFuncDatas(Context context) {
        List<FuncBean> datas = new ArrayList<>();
        datas.add(new FuncBean(context.getResources().getString(R.string.main_func_study), R.drawable.icon_func1_study));
        if (!getstrRes(context,R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
            datas.add(new FuncBean(context.getResources().getString(R.string.main_func_intelligent_reading), R.drawable.icon_func1_read));
        }
        datas.add(new FuncBean(context.getResources().getString(R.string.main_func_entertainment), R.drawable.icon_func1_play));
        datas.add(new FuncBean(context.getResources().getString(R.string.main_func_small_talk), R.drawable.icon_func1_chat));
        datas.add(new FuncBean(context.getResources().getString(R.string.main_func_call_phone), R.drawable.icon_func1_phone));
        datas.add(new FuncBean(context.getResources().getString(R.string.main_func_album), R.drawable.icon_func1_album));
        datas.add(new FuncBean(context.getResources().getString(R.string.main_func_setting), R.drawable.icon_func1_setting));
        return datas;
    }

    /**
     * 获取二级功能数据————学习
     *
     * @return
     */
    public static List<FuncBean> getTwoFuncStudyDatas(Context context) {
        List<FuncBean> datas = new ArrayList<>();
        datas.add(new FuncBean(context.getResources().getString(R.string.func_poetry_recite), R.drawable.icon_func2_poetry_recite));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_english_study), R.drawable.icon_func2_english_study));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_safety_education), R.drawable.icon_func2_safety_education));
        return datas;
    }

    /**
     * 获取二级功能数据————智慧阅读
     *
     * @param context
     * @return
     */
    public static List<FuncBean> getTwoFuncIntelligentReadingDatas(Context context) {
        List<FuncBean> datas = new ArrayList<>();
        datas.add(new FuncBean(context.getResources().getString(R.string.func_country_learn), R.drawable.icon_func2_country_learn));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_picture_book), R.drawable.icon_func2_picture_book));
        return datas;
    }

    /**
     * 获取二级功能数据————娱乐
     *
     * @return
     */
    public static List<FuncBean> getTwoFuncPlayDatas(Context context) {
        List<FuncBean> datas = new ArrayList<>();
        datas.add(new FuncBean(context.getResources().getString(R.string.func_nursery_rhymes), R.drawable.icon_func2_nursery_rhymes));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_story), R.drawable.icon_func2_story));
        if (getstrRes(context,R.string.vendor).equals(BaseConstant.VENDOR_JINGUOWEI)){
            datas.add(new FuncBean(context.getResources().getString(R.string.func_picture_books), R.drawable.icon_func2_country_learn));
        }
        datas.add(new FuncBean(context.getResources().getString(R.string.func_anim), R.drawable.icon_func2_anim));
        return datas;
    }

    /**
     * 获取英语学习数据
     * @param context
     * @return
     */
    public static List<FuncBean> getFuncEnglishStudyDatas(Context context) {
        List<FuncBean> datas = new ArrayList<>();
        datas.add(new FuncBean(context.getResources().getString(R.string.func_english_study_follow_me), R.drawable.icon_func3_english_study_follow_me));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_english_study_translation), R.drawable.icon_func3_english_study_translation));
        return datas;
    }

    /**
     * 获取跟我学数据
     *
     * @param context
     * @return
     */
    public static List<FuncBean> getFollowMeDatas(Context context) {
        List<FuncBean> datas = new ArrayList<>();
        datas.add(new FuncBean(context.getResources().getString(R.string.func_know_animal), R.drawable.icon_follow_me_know_animal));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_know_num), R.drawable.icon_follow_me_know_num));
        datas.add(new FuncBean(context.getResources().getString(R.string.func_know_fruit), R.drawable.icon_follow_me_know_fruit));
        return datas;
    }

    /**
     * 获取认识动物的数据
     *
     * @param context
     */
    public static List<FollowMeBean> getKnowAnimalDatas(Context context) {
        List<FollowMeBean> datas = new ArrayList<>();
        datas.add(getFollowMeBean(context, R.string.func_know_animal_dog, R.string.func_know_animal_dog_e, R.drawable.icon_follow_me_know_animal_card_dog));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_monkey, R.string.func_know_animal_monkey_e, R.drawable.icon_follow_me_know_animal_card_monkey));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_fox, R.string.func_know_animal_fox_e, R.drawable.icon_follow_me_know_animal_card_fox));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_tiger, R.string.func_know_animal_tiger_e, R.drawable.icon_follow_me_know_animal_card_tiger));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_horse, R.string.func_know_animal_horse_e, R.drawable.icon_follow_me_know_animal_card_house));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_cat, R.string.func_know_animal_cat_e, R.drawable.icon_follow_me_know_animal_card_cat));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_lion, R.string.func_know_animal_lion_e, R.drawable.icon_follow_me_know_animal_card_lion));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_panda, R.string.func_know_animal_panda_e, R.drawable.icon_follow_me_know_animal_card_panda));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_duck, R.string.func_know_animal_duck_e, R.drawable.icon_follow_me_know_animal_card_duck));
        datas.add(getFollowMeBean(context, R.string.func_know_animal_fish, R.string.func_know_animal_fish_e, R.drawable.icon_follow_me_know_animal_card_fish));
        return datas;
    }

    /**
     * 获取认识数字的数据
     *
     * @param context
     * @return
     */
    public static List<FollowMeBean> getKnowNumDatas(Context context) {
        List<FollowMeBean> datas = new ArrayList<>();
        datas.add(getFollowMeBean(context, R.string.func_know_num_zero, R.string.func_know_num_zero_e, R.drawable.icon_follow_me_know_num_card_zero));
        datas.add(getFollowMeBean(context, R.string.func_know_num_one, R.string.func_know_num_one_e, R.drawable.icon_follow_me_know_num_card_one));
        datas.add(getFollowMeBean(context, R.string.func_know_num_two, R.string.func_know_num_two_e, R.drawable.icon_follow_me_know_num_card_two));
        datas.add(getFollowMeBean(context, R.string.func_know_num_three, R.string.func_know_num_three_e, R.drawable.icon_follow_me_know_num_card_three));
        datas.add(getFollowMeBean(context, R.string.func_know_num_fore, R.string.func_know_num_fore_e, R.drawable.icon_follow_me_know_num_card_fore));
        datas.add(getFollowMeBean(context, R.string.func_know_num_five, R.string.func_know_num_five_e, R.drawable.icon_follow_me_know_num_card_five));
        datas.add(getFollowMeBean(context, R.string.func_know_num_six, R.string.func_know_num_six_e, R.drawable.icon_follow_me_know_num_card_six));
        datas.add(getFollowMeBean(context, R.string.func_know_num_seven, R.string.func_know_num_seven_e, R.drawable.icon_follow_me_know_num_card_seven));
        datas.add(getFollowMeBean(context, R.string.func_know_num_eight, R.string.func_know_num_eight_e, R.drawable.icon_follow_me_know_num_card_eight));
        datas.add(getFollowMeBean(context, R.string.func_know_num_nine, R.string.func_know_num_nine_e, R.drawable.icon_follow_me_know_num_card_nine));
        datas.add(getFollowMeBean(context, R.string.func_know_num_ten, R.string.func_know_num_ten_e, R.drawable.icon_follow_me_know_num_card_ten));
        return datas;
    }

    /**
     * 获取认识水果的数据
     *
     * @param context
     * @return
     */
    public static List<FollowMeBean> getKnowFruitDatas(Context context) {
        List<FollowMeBean> datas = new ArrayList<>();
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_strawberry, R.string.func_know_fruit_strawberry_e, R.drawable.icon_follow_me_know_fruit_card_strawberry));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_orange, R.string.func_know_fruit_orange_e, R.drawable.icon_follow_me_know_fruit_card_orange));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_mongo, R.string.func_know_fruit_mongo_e, R.drawable.icon_follow_me_know_fruit_card_mongo));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_lemon, R.string.func_know_fruit_lemon_e, R.drawable.icon_follow_me_know_fruit_card_lemon));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_apple, R.string.func_know_fruit_apple_e, R.drawable.icon_follow_me_know_fruit_card_apple));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_grapes, R.string.func_know_fruit_grapes_e, R.drawable.icon_follow_me_know_fruit_card_grapes));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_peach, R.string.func_know_fruit_peach_e, R.drawable.icon_follow_me_know_fruit_card_peach));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_watermelon, R.string.func_know_fruit_watermelon_e, R.drawable.icon_follow_me_know_fruit_card_watermelon));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_banana, R.string.func_know_fruit_banana_e, R.drawable.icon_follow_me_know_fruit_card_banana));
        datas.add(getFollowMeBean(context, R.string.func_know_fruit_pear, R.string.func_know_fruit_pear_e, R.drawable.icon_follow_me_know_fruit_card_pear));
        return datas;
    }


    public static FuncBean getFuncBean(Context context, int resId, int iconId) {
        return new FuncBean(context.getResources().getString(resId), iconId);
    }

    public static FollowMeBean getFollowMeBean(Context context, int chineseId, int englishd, int iconId) {
        return new FollowMeBean(context.getResources().getString(chineseId), context.getResources().getString(englishd), iconId);
    }

    public static void setFollowMeContent(Context context, ImageView imageView, RelativeLayout rlBg, String name) {
        int ivRes = R.drawable.icon_follow_me_know_animal_dog;
        int bgRes =R.color.base_color_dog;
        /*动物*/
        if (getstrRes(context, R.string.func_know_animal_dog).equals(name)) {//狗
            ivRes = R.drawable.icon_follow_me_know_animal_dog;
            bgRes = R.color.base_color_dog;
        } else if (getstrRes(context, R.string.func_know_animal_monkey).equals(name)) {//猴子
            ivRes = R.drawable.icon_follow_me_know_animal_monkey;
            bgRes = R.color.base_color_monkey;
        } else if (getstrRes(context, R.string.func_know_animal_fox).equals(name)) {//狐狸
            ivRes = R.drawable.icon_follow_me_know_animal_fox;
            bgRes = R.color.base_color_fox;
        } else if (getstrRes(context, R.string.func_know_animal_tiger).equals(name)) {//老虎
            ivRes = R.drawable.icon_follow_me_know_animall_tiger;
            bgRes = R.color.base_color_tiger;
        } else if (getstrRes(context, R.string.func_know_animal_horse).equals(name)) {//马
            ivRes = R.drawable.icon_follow_me_know_animal_horse;
            bgRes = R.color.base_color_horse;
        } else if (getstrRes(context, R.string.func_know_animal_cat).equals(name)) {//猫咪
            ivRes = R.drawable.icon_follow_me_know_animal_cat;
            bgRes = R.color.base_color_cat;
        } else if (getstrRes(context, R.string.func_know_animal_lion).equals(name)) {//狮子
            ivRes = R.drawable.icon_follow_me_know_animal_lion;
            bgRes = R.color.base_color_lemon;
        } else if (getstrRes(context, R.string.func_know_animal_panda).equals(name)) {//熊猫
            ivRes = R.drawable.icon_follow_me_know_animal_panda;
            bgRes = R.color.base_color_panda;
        } else if (getstrRes(context, R.string.func_know_animal_duck).equals(name)) {//鸭子
            ivRes = R.drawable.icon_follow_me_know_animal_duck;
            bgRes = R.color.base_color_duck;
        } else if (getstrRes(context, R.string.func_know_animal_fish).equals(name)) {//鱼
            ivRes = R.drawable.icon_follow_me_know_animal_fish;
            bgRes = R.color.base_color_fish;
        }
        /*数字*/
        else if (getstrRes(context, R.string.func_know_num_zero).equals(name)) {//0
            ivRes = R.drawable.icon_follow_me_know_num_zero;
            bgRes = R.color.base_color_zero;
        } else if (getstrRes(context, R.string.func_know_num_one).equals(name)) {//1
            ivRes = R.drawable.icon_follow_me_know_num_one;
            bgRes = R.color.base_color_one;
        } else if (getstrRes(context, R.string.func_know_num_two).equals(name)) {//2
            ivRes = R.drawable.icon_follow_me_know_num_two;
            bgRes = R.color.base_color_two;
        } else if (getstrRes(context, R.string.func_know_num_three).equals(name)) {//3
            ivRes = R.drawable.icon_follow_me_know_num_three;
            bgRes = R.color.base_color_three;
        } else if (getstrRes(context, R.string.func_know_num_fore).equals(name)) {//4
            ivRes = R.drawable.icon_follow_me_know_num_fore;
            bgRes = R.color.base_color_fore;
        } else if (getstrRes(context, R.string.func_know_num_five).equals(name)) {//5
            ivRes = R.drawable.icon_follow_me_know_num_five;
            bgRes = R.color.base_color_five;
        } else if (getstrRes(context, R.string.func_know_num_six).equals(name)) {//6
            ivRes = R.drawable.icon_follow_me_know_num_six;
            bgRes = R.color.base_color_six;
        } else if (getstrRes(context, R.string.func_know_num_seven).equals(name)) {//7
            ivRes = R.drawable.icon_follow_me_know_num_seven;
            bgRes = R.color.base_color_seven;
        } else if (getstrRes(context, R.string.func_know_num_eight).equals(name)) {//8
            ivRes = R.drawable.icon_follow_me_know_num_eight;
            bgRes = R.color.base_color_eight;
        } else if (getstrRes(context, R.string.func_know_num_nine).equals(name)) {//9
            ivRes = R.drawable.icon_follow_me_know_num_nine;
            bgRes = R.color.base_color_nine;
        } else if (getstrRes(context, R.string.func_know_num_ten).equals(name)) {//10
            ivRes = R.drawable.icon_follow_me_know_num_ten;
            bgRes = R.color.base_color_ten;
        }
        /*水果*/
        else if (getstrRes(context, R.string.func_know_fruit_strawberry).equals(name)) {//草莓
            ivRes = R.drawable.icon_follow_me_know_fruit_strawberry;
            bgRes = R.color.base_color_strawberry;
        } else if (getstrRes(context, R.string.func_know_fruit_orange).equals(name)) {//橙子
            ivRes = R.drawable.icon_follow_me_know_fruit_orange;
            bgRes = R.color.base_color_orange;
        } else if (getstrRes(context, R.string.func_know_fruit_mongo).equals(name)) {//芒果
            ivRes = R.drawable.icon_follow_me_know_fruit_mongo;
            bgRes = R.color.base_color_mongo;
        } else if (getstrRes(context, R.string.func_know_fruit_lemon).equals(name)) {//柠檬
            ivRes = R.drawable.icon_follow_me_know_fruit_lemon;
            bgRes = R.color.base_color_lemon;
        } else if (getstrRes(context, R.string.func_know_fruit_apple).equals(name)) {//苹果
            ivRes = R.drawable.icon_follow_me_know_fruit_apple;
            bgRes = R.color.base_color_apple;
        } else if (getstrRes(context, R.string.func_know_fruit_grapes).equals(name)) {//葡萄
            ivRes = R.drawable.icon_follow_me_know_fruit_grapes;
            bgRes = R.color.base_color_grapes;
        } else if (getstrRes(context, R.string.func_know_fruit_peach).equals(name)) {//桃子
            ivRes = R.drawable.icon_follow_me_know_fruit_peach;
            bgRes = R.color.base_color_peach;
        } else if (getstrRes(context, R.string.func_know_fruit_watermelon).equals(name)) {//西瓜
            ivRes = R.drawable.icon_follow_me_know_fruit_watermelon;
            bgRes = R.color.base_color_watermelon;
        } else if (getstrRes(context, R.string.func_know_fruit_banana).equals(name)) {//香蕉
            ivRes = R.drawable.icon_follow_me_know_fruit_banana;
            bgRes = R.color.base_color_banana;
        } else if (getstrRes(context, R.string.func_know_fruit_pear).equals(name)) {//雪梨
            ivRes = R.drawable.icon_follow_me_know_fruit_pear;
            bgRes = R.color.base_color_pear;
        }

        imageView.setBackgroundResource(ivRes);
        rlBg.setBackgroundResource(bgRes);
    }

    public static String getstrRes(Context context, int resId) {
        return context.getResources().getString(resId);
    }


}
