import Vue from 'vue'
import 'api/resourse'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import App from 'pages/App.vue'
import store from 'store/store'
import router from 'router/router'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify, {
  breakpoint: {
    thresholds: {
      xs:  864,
      sm: 1034,
      md: 1904
    }
  }
});

new Vue({
  el: '#app',
  store,
  router,
  render: a => a(App)
});
