import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import SvgMap from './views/SvgMap.vue';
import SvgMapView from './views/SvgMapView.vue';
import MapConstructor from './views/MapConstructor.vue';
import Question from './views/Question.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/question',
      name: 'question',
      component: Question,
      props: route => ({ query: route.query.q })
    },
    {
      path: '/create',
      name: 'createMap',
      component: MapConstructor,
      props: route => ({ query: route.query.q })
      // component: SvgMap,
      // props: (route) => ({ query: route.query.q })
    },
    {
      path: '/map',
      name: 'viewMap',
      component: SvgMapView,
      props: (route) => ({ query: route.query.q })
    }
  ]
})
