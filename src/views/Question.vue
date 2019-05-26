<template>
  <v-container>
    <v-dialog v-model="this.model">
      <v-card class="text-xs-center">
        <v-card-text>
          Укажите размер области:
        </v-card-text>
        <v-card-text>
          <v-text-field v-model="wText">
            Ширина
          </v-text-field>
          <v-text-field v-model="hText">
            Высота
          </v-text-field>
        </v-card-text>
        <v-card-actions>
          <router-link v-bind:to="'/create?id=' + this.$route.query.id + '&width=' + this.wText +
'&height=' + this.hText" tag="span">
            <v-btn>
              OK
            </v-btn>
          </router-link>
          <router-link v-bind:to="'/'" tag="span">
            <v-btn>
              Отмена
            </v-btn>
          </router-link>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
  export default {
    data:() =>({
      wText: 10,
      hText: 10,
      model: true
    }),
    name: "Question"
  }
</script>

<style scoped>

</style>
