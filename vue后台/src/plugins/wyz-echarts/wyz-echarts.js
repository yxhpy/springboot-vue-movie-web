import Vue from 'vue';
import echarts from 'echarts';
import sweetLight from '../../plugins/wyz-echarts/theme/sweet-light.json';
import sweetLight16 from '../../plugins/wyz-echarts/theme/sweet-light-16.json';

echarts.registerTheme('sweet-light', sweetLight);
echarts.registerTheme('sweet-light-16', sweetLight16);
Vue.prototype.$echarts = echarts;
