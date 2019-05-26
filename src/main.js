import './styles/index.css'
import Vue from 'vue'
import App from './App.vue'
import Vuetify from 'vuetify'
import store from './store/store.js'
import 'vue-resource'
import router from './router'
import 'vuetify/dist/vuetify.min.css'
import YmapPlugin from 'vue-yandex-maps'

Vue.config.productionTip = false;

Vue.use(Vuetify, YmapPlugin);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
