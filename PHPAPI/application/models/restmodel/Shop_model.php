<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

Class Shop_model extends CI_Model {

    public function __construct() {
        parent::__construct();
    }

    public function create($table, $data) {
        if (!$this->is_exist($table, $data)) {
            if ($this->db->insert($table, $data)) {

                return $this->get_shop_information($this->db->insert_id());
            } else {
                return NULL;
            }
        } else {
            return FALSE;
        }
    }

    public function update($table, $data, $where) {
        if ($this->is_exist($table, $where)) {
            $this->db->where($where);
            if ($this->db->update($table, $data)) {
                return $this->get_shop_information($where['id']);
            } else {
                return NULL;
            }
        }
    }

    public function remove($table, $where) {
        if ($this->is_exist($table, $where)) {
            $this->db->where($where);
            if ($this->db->delete($table)) {
                return TRUE;
            } else {
                return NULL;
            }
        }
    }

    public function get_shop_information() {
        return $this->db->select('*')->from('tbl_shops')
//                ->where('id', $insert_id)
                ->get()->result();
    }

    public function is_exist($table, $data) {
        $user_sav = $this->db->select("*")->from($table)->where($data);
        return $user_sav->count_all_results();
    }

}
