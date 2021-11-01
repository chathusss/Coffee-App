<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

Class User_model extends CI_Model {

    public function __construct() {
        parent::__construct();
    }

    public function create($table, $data) {
        if (!$this->is_exist($table, $data)) {
            if ($this->db->insert($table, $data)) {

                return $this->get_user_information($this->db->insert_id());
            } else {
                return NULL;
            }
        } else {
            return FALSE;
        }
    }

    public function get_user_information($insert_id) {
        return $this->db->select('id,username')->from('tbl_users')->where('id', $insert_id)->get()->result();
    }

    public function access_request($access_data) {
        $user_sav = $this->db->select("*")->from('tbl_users')->where($access_data)->count_all_results();
        if ($user_sav > 0) {
            $access_user = $this->db->select("*")->from('tbl_users')->where($access_data)->get()->result();
            
            return $this->db->select('*,users.id as userid,shop.id as shopid')->from('tbl_users users')
                    ->join("tbl_shops shop","shop.created_by=users.id")
                    ->where('users.id', $access_user[0]->id)->get()->result();
        } else {
            return NULL;
        }
    }

    public function is_exist($table, $data) {
        $user_sav = $this->db->select("*")->from($table)->where($data);
        return $user_sav->count_all_results();
    }

}
