export default defineEventHandler(async (event)=>{
    const {public:{apiBaseUrl}} = useRuntimeConfig()
    const response = await $fetch(`${apiBaseUrl}/api/v1/view/activity`)
    return response
})
