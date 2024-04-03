import { customAxios } from '@/shared';
import Swal from 'sweetalert2';

export async function acceptRegist(postDto: Object) {
    return await customAxios.post('/team/approve', postDto).catch((err) => err);
}
