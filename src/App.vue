<template>
  <div id="app">
    <v-app>
    <v-content fill class="pt-0">
        <router-view/>
    </v-content>
        <toolbar style="z-index: 100; position: absolute; width: 90%;"/>
        <navigation-drawer />
    </v-app>
  </div>
</template>
<script>
  import Toolbar from './components/Toolbar.vue';
  import NavigationDrawer from './components/NavigationDrawer.vue';

  export default {
    components: { Toolbar, NavigationDrawer },
  }
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
