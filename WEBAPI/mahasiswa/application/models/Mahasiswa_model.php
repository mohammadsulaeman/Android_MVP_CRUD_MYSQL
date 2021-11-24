<?php

class Mahasiswa_model extends CI_Model
{
    public function edititem($dataitem, $id_siswa)
    {
        $this->db->where('id_siswa', $id_siswa);
        $this->db->update('siswa', $dataitem);
        return true;
    }

    public function deleteitem($id_siswa)
    {
        $this->db->where('id_siswa', $id_siswa);
        $this->db->delete('siswa');
        return true;
    }
}
