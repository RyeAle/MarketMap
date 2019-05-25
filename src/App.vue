<template>
  <div id="app">
    <v-app>
    <v-content fill class="pt-0">
        <router-view/>
    </v-content>

    </v-app>
  </div>
</template>
<script>

  export default {
    methods: {
      geoRequest: () => {
        navigator.permissions.query({name:'geolocation'}).then(function(result) {});
      }
    },
    mounted() {
      this.geoRequest()
    }
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
