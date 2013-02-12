// -*-C++-*-
/*!
 * @file  MyServiceModuleTypeDefSVC_impl.h
 * @brief Service implementation header of MyServiceModuleTypeDef.idl
 *
 */

#include "MyServiceModuleTypeDefSkel.h"

#ifndef MYSERVICEMODULETYPEDEFSVC_IMPL_H
#define MYSERVICEMODULETYPEDEFSVC_IMPL_H
 
/*!
 * @class MyServiceSVC_impl
 * Example class implementing IDL interface MyService
 */
class MyServiceSVC_impl
 : public virtual POA_MyService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyServiceSVC_impl();

   // attributes and operations
   Time echo(Time_out msg, const char* str);

};



#endif // MYSERVICEMODULETYPEDEFSVC_IMPL_H


