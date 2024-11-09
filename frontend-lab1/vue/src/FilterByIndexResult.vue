<script>
export default {
  data() {
    return {
      dots: new Array(0)
    }
  },
  methods: {
    filter() {
      fetch(`http://localhost:1298/api/filter/groupById`, {
        method: "GET",
        headers: new Headers({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        })
      }).then(request =>
      {
        return request.json()
      }).then(request =>
        {
          this.dots=request
        }
      )
    }
  },
  created() {
    this.filter()
  }
}
</script>

<template>
  <div>
    <table>
      <thead>
      <th>id</th>
      <th>count</th>
      </thead>
      <tbody>
      <tr v-for="dot in dots" :key="dot">
        <td>{{dot.id}}</td>
        <td>{{dot.count}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>
