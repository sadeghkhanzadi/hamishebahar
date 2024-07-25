<script setup lang="ts">
const emit = defineEmits(['edit', 'delete', 'show'])
const {thead, tbody, title} = defineProps(['thead', 'tbody', 'title'])
</script>

<template>
  <section class="bg-white flex flex-col gap-y-3  p-5 rounded overflow-x-auto">
    <div><h3 class="font-bold">{{ title }}</h3></div>
    <table>
      <thead>
      <tr>
        <th v-for="(item , index) in thead" class="px-3" :key="index">{{ item.name }}</th>
      </tr>
      </thead>
      <tbody v-if="tbody && tbody.length ">
      <tr v-for="(item , index) in tbody" :key="index" :class="{'bg-red-100':item.is_deleted === true}">
        <td class="px-2">{{ index + 1 }}</td>
        <td class="px-2">{{ item.firstName }}</td>
        <td class="px-2">{{ item.lastName.length > 30 ? item.lastName.substr(0, 15) + '...' : item.lastName }}</td>
        <td class="px-2">{{ item.age }} سال</td>
        <td class="px-2">{{ item.workExperience }} سال</td>
        <td class="px-2">{{ item.jobTitle }}</td>
        <td class="px-2"><a :href="`tel:${item.phoneNumber}`">{{ item.phoneNumber }}</a></td>
        <td class="px-2">{{ item.teacherCode }}</td>
        <td class="px-2 rounded status"><span
            :class="` w-fit mx-auto px-3 py-1 rounded ${ item.is_active ? 'bg-blue-500' :'bg-red-500'}`">{{
            item.is_active === true ? 'فعال' : "غیرفعال"
          }}</span>
        </td>
        <td class=" px-2 py-1 flex flex-col md:flex-row gap-1 ">
          <button
              @click="emit('show',tbody[index])"
              class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-blue-700 text-white bg-blue-500 inline-flex items-center justify-center rounded px-2 py-3">
            <i class="fa-solid fa-eye fa-xs"/>
          </button>
          <button
              @click="emit('edit',item.id)"
              class=" mx-1 cursor-pointer transition duration-200 ease-in hover:bg-yellow-700 text-white bg-yellow-500 inline-flex items-center justify-center rounded px-2 py-3">
            <i class="fa-solid fa-pen fa-xs"/>
          </button>
          <button
              @click="emit('delete', {name:item.firstName , lastName:item.lastName , id:item.id})"
              :disabled="item.is_deleted"
              :class="{'opacity-30':item.is_deleted , 'cursor-not-allowed' : item.is_deleted}"
              class=" mx-1 cursor-pointer transition  duration-200 ease-in hover:bg-red-700 text-white bg-red-500 inline-flex items-center justify-center rounded px-2 py-3">
            <i class="fa-solid fa-trash-alt fa-xs"/>
          </button>
        </td>
      </tr>
      </tbody>
    </table>
  </section>
</template>

<style scoped lang="scss">
table, th, td {
  border: 1px solid rgba(0, 0, 0, 0.15);
  text-align: center;
  font-size: var(--text-md);
  font-weight: var(--font-medium);
  padding-top: 5px;
  padding-bottom: 5px;
}

th {
  font-size: var(--text-md);
  font-weight: var(--font-bold);
}

.status {
  color: #fff;

  span {
    font-size: var(--text-md);
    display: flex;
    justify-content: center;
  }
}
</style>
