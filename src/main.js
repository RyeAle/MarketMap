import Vue from 'vue'
import App from './App.vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import store from 'store/store'
import 'api/resourse'
import router from './router'

Vue.config.productionTip = false;

Vue.use(Vuetify);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
