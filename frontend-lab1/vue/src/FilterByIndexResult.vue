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
    },
    close(){
      this.$router.push({name: 'main'});
    }
  },
  created() {
    this.filter()
  },
  mounter:function () {
    window.setInterval(() => {
      this.filter()
    }, 3000)
  }
}
</script>

<template>
  <div>
    <div>
      <button style="margin-left: 80%" type="submit" @click="close">Закрыть вкладку</button>
    </div>
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

div {
  margin: 20px;
}

button[type="submit"] {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 10px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s;
}


table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
  text-align: left;
}

button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #45a049;
}
</style>
